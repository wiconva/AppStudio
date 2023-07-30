document.write("<div>"+
               " <nav>"+
                    "<a href=\"#\" id='menucliente'><button onmouseover='submenuCliente()'>Clientes</button></a>"+
                "</nav>"+
            "</div>"+
    "<div id='subcliente' hidden= 'hidden' onmouseleave='submenuCliente()'>"+
        "<a href='CrearCliente.html' ><button >Crear Cliente</button></a><br>"+
        "<a href='ConsultarClientes.html'><button>Consultar Clientes</button></a><br>"+
        "<a href='ConsultarCliente.html'><button>Consultar Cliente</button></a><br>"+
        "<a href='EliminarCliente.html'><button>Eliminar Cliente</button></a><br>"+
        "<a href='ActualizarCliente.html'><button>Actualizar Cliente</button></a><br>"+
    "</div>");