<section id="actions" class="py-4 mb-4 bg-light">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-3">
                <a href="index.jsp" class="btn btn-light btn-block">
                    Regresar a inicio
                </a>
            </div>
            <div class="col-md-3">
                <button type="submit" class="btn btn-success btn-block">
                    Guardar modificación
                </button>
            </div>
            <div class="col-md-3">
                <a href="${pageContext.request.contextPath}/servletControlador?accion=eliminar&idHardware=${hardware.idHardware}" class="btn btn-danger btn-block">
                    Eliminar
                </a>
            </div>
        </div>
    </div>
</section>
