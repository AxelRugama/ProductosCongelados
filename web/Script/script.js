
function divProductos(nombre, precio, imagen) {
var ret = "";
        ret += '<div class="col-sm-5" id="content"><a href="Pedidos.xhtml"> ';
        ret += '<p:graphicImage url="' + imagen + '"/>';
        ret += '<h:outputText value="' + nombre + '"/>';
        ret += '<h:outputText value="'+"Precio: " + precio + '"/>';
        ret += '</a></div>';
        Console.log(ret);
        return ret;
        }

function hacerDiv(nombre,precio,imagen)
{
    var obj = document.getElementById("pageContent");
    obj.innerHTML = divProductos(nombre,precio,imagen);
}

