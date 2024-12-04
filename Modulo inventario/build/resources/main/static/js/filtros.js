function openModal() {
    document.getElementById("modalOverlay").style.display = "flex";
}

function closeModal() {
    document.getElementById("modalOverlay").style.display = "none";
}

function addRow() {
    const filterTableBody = document.getElementById("filterTableBody");
    const newRow = document.createElement("tr");
    newRow.innerHTML = `
        <td>
            <select onchange="updateOrderOptions(this)">
                <option value="id">ID</option>
                <option value="contacto">Contacto</option>
                <option value="fechaProgramada">Fecha Programada</option>
                <option value="documentoOrigen">Documento de Origen</option>
                <option value="estado">Estado</option>
            </select>
        </td>
        <td>
            <select>
                <option value="asc">Ascendente</option>
                <option value="desc">Descendente</option>
            </select>
        </td>
        <td><button class="btn-action" onclick="removeRow(this)">Borrar</button></td>
    `;
    filterTableBody.appendChild(newRow);
}

function removeRow(button) {
    button.closest("tr").remove();
}

function acceptFilters() {
    applyFilters();
    closeModal();
}

function updateOrderOptions(selectElement) {
    const orderSelect = selectElement.parentNode.nextElementSibling.firstElementChild;
    if (selectElement.value === "estado") {
        orderSelect.innerHTML = `
            <option value="listo">Listo</option>
            <option value="proceso">En Proceso</option>
        `;
    } else {
        orderSelect.innerHTML = `
            <option value="asc">Ascendente</option>
            <option value="desc">Descendente</option>
        `;
    }
}

function applyFilters() {
    const filterTableBody = document.getElementById("filterTableBody");
    const rows = Array.from(document.querySelectorAll(".recepciones-container tbody tr"));

    // Primero, mostramos todas las filas antes de aplicar los filtros.
    rows.forEach(row => (row.style.display = ""));

    Array.from(filterTableBody.children).forEach(filterRow => {
        const field = filterRow.cells[0].querySelector("select").value;
        const order = filterRow.cells[1].querySelector("select").value;

        if (field === "estado") {
            rows.forEach(row => {
                const status = row.querySelector(".status").textContent.trim().toLowerCase();
                if (status !== order) {
                    row.style.display = "none";
                }
            });
        } else {
            rows.sort((a, b) => {
                const aValue = a.querySelector(`td:nth-child(${getFieldIndex(field)})`).textContent.trim();
                const bValue = b.querySelector(`td:nth-child(${getFieldIndex(field)})`).textContent.trim();

                if (field === "fechaProgramada") {
                    const dateA = new Date(aValue);
                    const dateB = new Date(bValue);
                    return order === "asc" ? dateA - dateB : dateB - dateA;
                } else {
                    return order === "asc" ? aValue.localeCompare(bValue) : bValue.localeCompare(aValue);
                }
            });

            // Ordena visualmente las filas en la tabla
            const tbody = document.querySelector(".recepciones-container tbody");
            rows.forEach(row => tbody.appendChild(row));
        }
    });
}

// Helper para obtener el índice de la columna basado en el campo
function getFieldIndex(field) {
    switch (field) {
        case "id":
            return 1;
        case "contacto":
            return 2;
        case "fechaProgramada":
            return 3;
        case "documentoOrigen":
            return 4;
        case "estado":
            return 5;
        default:
            return 1;
    }
}
