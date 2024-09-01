function hacerLogin() {
    let cedula = document.getElementById("cedulaTxt").value
    window.location.href = "http://localhost:8099/mascotas/mis-mascotas/" + cedula;
    return false;
}