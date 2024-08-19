document.addEventListener('DOMContentLoaded', function () {
    const fotoInput = document.getElementById('foto');
    let img = document.createElement('img');

    fotoInput.addEventListener('input', function () {
        const url = fotoInput.value;
        if (url) {
            if (img.parentNode) {
                img.src = url;
            } else {
                img.src = url;
                img.alt = 'Foto de cliente';
                img.style.height = '10em';
                img.style.width = '10em';
                img.style.objectFit = 'contain';
                const fotoDefault = document.getElementById('clienteFoto');
                fotoDefault.replaceWith(img);
            }
        }
    });
});