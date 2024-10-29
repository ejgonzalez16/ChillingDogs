let seccionNumericos = document.getElementById("seccionNumericos");
let seccionImagenes = document.getElementById("seccionImagenes");

//Obtener la altura de numericos
let alturaNumericos = seccionNumericos.offsetHeight;

//Aplicar la altura de numericos a imagenes
seccionImagenes.style.height = alturaNumericos + "px";