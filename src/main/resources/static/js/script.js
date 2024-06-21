document.addEventListener('DOMContentLoaded', function() {
    const tableBody = document.querySelector("#dentistsTableBody");

    function fetchDentists() {
        fetch(`/odontologo`)
            .then(response => response.json())
            .then(data => {
                tableBody.innerHTML = "";
                data.forEach(dentist => {
                    const row = document.createElement("tr");
                    row.innerHTML = `
                        <td>${dentist.id}</td>
                        <td>${dentist.nombre}</td>
                        <td>${dentist.apellido}</td>
                        <td>${dentist.nroMatricula}</td>
                        <td>
                            <button class="btn btn-primary btn-sm edit-button" data-id="${dentist.id}" data-name="${dentist.nombre}" data-surname="${dentist.apellido}" data-registrationNumber="${dentist.nroMatricula}">Edit</button>
                            <button class="btn btn-danger btn-sm delete-button" data-id="${dentist.id}">Delete</button>
                        </td>
                    `;
                    tableBody.appendChild(row);
                });
            })
            .catch(error => {
                console.error('Error fetching dentists:', error)
                alert(error)
                });
    }

    document.querySelector("#addDentistForm").addEventListener("submit", function(event) {
        event.preventDefault();
        const nombre = document.querySelector("#addName").value;
        const apellido = document.querySelector("#addSurname").value;
        const nroMatricula = document.querySelector("#addRegistrationNumber").value;

        fetch('/odontologo', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ nombre, apellido, nroMatricula }),
        })
        .then(response => {
            if (!response.ok) throw new Error('Error adding dentist');
            return response.json();
        })
        .then(() => {
            $('#addDentistModal').modal('hide');
            fetchDentists();
        })
        .catch(error => {
                        console.error('Error fetching dentists:', error)
                        alert(error)
                        });
    });

    document.querySelector("#editDentistForm").addEventListener("submit", function(event) {
        event.preventDefault();
        const id = document.querySelector("#editId").value;
        const nombre = document.querySelector("#editName").value;
        const apellido = document.querySelector("#editSurname").value;
        const nroMatricula = document.querySelector("#editRegistrationNumber").value;

        fetch(`/odontologo`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ id, nombre, apellido, nroMatricula }),
        })
        .then(response => {
            if (!response.ok) throw new Error('Error updating dentist');
            return response.json();
        })
        .then(() => {
            $('#editDentistModal').modal('hide');
            fetchDentists();
        })
        .catch(error => {
                        console.error('Error fetching dentists:', error)
                        alert(error)
                        });
    });

    tableBody.addEventListener('click', function(event) {
        const target = event.target;
        if (target.classList.contains('edit-button')) {
            const id = target.getAttribute('data-id');
            const nombre = target.getAttribute('data-name');
            const apellido = target.getAttribute('data-surname');
            const nroMatricula = target.getAttribute('data-registrationNumber');

            document.querySelector("#editId").value = id;
            document.querySelector("#editName").value = nombre;
            document.querySelector("#editSurname").value = apellido;
            document.querySelector("#editRegistrationNumber").value = nroMatricula;

            $('#editDentistModal').modal('show');
        } else if (target.classList.contains('delete-button')) {
            const id = target.getAttribute('data-id');
            if (confirm("Are you sure you want to delete this dentist?")) {
                fetch(`/odontologo/${id}`, {
                    method: 'DELETE',
                })
                .then(response => {
                    if (!response.ok) throw new Error('Error deleting dentist');
                    fetchDentists();
                })
                .catch(error => {
                                console.error('Error fetching dentists:', error)
                                alert(error)
                                });
            }
        }
    });

    fetchDentists();
});
