document.addEventListener('DOMContentLoaded', function () {
    const fotoInput = document.getElementById('foto');
    let img = document.createElement('img');
    const url = fotoInput.value;
    if (url !== '') {
        img.src = url;
        img.alt = 'Foto de registro';
        img.style.height = '10em';
        img.style.width = '10em';
        img.style.objectFit = 'contain';
        const fotoDefault = document.getElementById('fotoRegistro');
        fotoDefault.replaceWith(img);
    }

    let input = document.getElementById('estado');
    if (input.value === 'Activo') {
        selectButton(document.getElementById('btnActivo'), 'green');
    } else if (input.value === 'Inactivo') {
        selectButton(document.getElementById('btnInactivo'), 'red');
    }

    fotoInput.addEventListener('input', function () {
        const url = fotoInput.value;
        if (url) {
            if (img.parentNode) {
                img.src = url;
            } else {
                img.src = url;
                img.alt = 'Foto de registro';
                img.style.height = '10em';
                img.style.width = '10em';
                img.style.objectFit = 'contain';
                const fotoDefault = document.getElementById('fotoRegistro');
                fotoDefault.replaceWith(img);
            }
        }
    });
});

function selectButton(button, color) {
    // Rellenar el botón para el formulario
    let input = document.getElementById('estado');
    input.value = button.textContent;

    // Deseleccionar todos los botones
    let buttons = button.parentElement.children;
    console.log(buttons);
    // Recorrer los botones y cambiar su color
    for (let i = 0; i < buttons.length; i++) {
        buttons[i].classList.remove("estadoActivo", "estadoInactivo");
        buttons[i].classList.add("btn-grey");
    }

    // Seleccionar el botón elegido y cambiar su color
    if (color === "green") {
        button.classList.remove("btn-grey");
        button.classList.add("estadoActivo");
    } else if (color === "red") {
        button.classList.remove("btn-grey");
        button.classList.add("estadoInactivo");
    } else {
        button.classList.remove("btn-grey");
    }
}