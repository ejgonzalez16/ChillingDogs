// Función para que se muestre la primera imagen del carrusel
function ponerPrimeraImagenActiva() {
    var carrusel = document.querySelector(".carousel-inner");
    carrusel.children[0].classList.add("active");
}

function detallesMascota(id){
    window.location.href = "http://localhost:8099/mascotas/" + id;
}

function moverCarrusel(direccion) {
    var carrusel = document.querySelector(".carousel-inner");
    var card = carrusel.children;
    var cardActiva = document.querySelector(".carousel-item.active");
    var indiceCardActiva = Array.from(card).indexOf(cardActiva);
    var indiceNuevaCard = 0;
    if (direccion === -1) {
        if (indiceCardActiva === 0) {
            indiceNuevaCard = card.length - 1;
        } else {
            indiceNuevaCard = indiceCardActiva - 1;
        }
    } else {
        if (indiceCardActiva === card.length - 1) {
            indiceNuevaCard = 0;
        } else {
            indiceNuevaCard = indiceCardActiva + 1;
        }
    }
    cardActiva.classList.remove("active");
    card[indiceNuevaCard].classList.add("active");
}

// Llamada a la función para poner la primera imagen del carrusel activa
ponerPrimeraImagenActiva();