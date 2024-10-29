function hacerLogin() {
    let cedula = document.getElementById("cedulaTxt").value
    window.location.href = "http://localhost:8099/mascotas/mis-mascotas/" + cedula;
    return false;
}

function entrarComoVeterinario() {
    window.location.href = "http://localhost:8099/mascotas/buscar";
    return false;
}

function crearMascota() {
    window.location.href = "http://localhost:8099/mascotas/registrar";
    return false;
}

function crearCliente() {
    window.location.href = "http://localhost:8099/clientes/registrar";
    return false;
}