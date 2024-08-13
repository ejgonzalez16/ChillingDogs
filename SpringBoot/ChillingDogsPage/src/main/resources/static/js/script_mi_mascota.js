let mascota = localStorage.getItem("mascota")
let tituloH1 = document.getElementById("titulo")
alert(mascota)
tituloH1.textContent += mascota.nombre