<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_AR"/>
<section id="harwares">
    <div class="container-fluid">
        <div class="row">
            <div class="col-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Productos agregados</h4>
                    </div>
                    <table class="table table-striped table-responsive">
                        <thead class="thead-dark"> 
                            <tr>
                                <th>#</th>
                                <th>Nombre</th>
                                <th>Marca</th>
                                <th>Categoria</th>
                                <th>Cantidad</th>
                                <th>Precio</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="hardware" items="${hardwares}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${hardware.nombre}</td>
                                    <td>${hardware.marca}</td>
                                    <td>${hardware.categoria}</td>
                                    <td>${hardware.cantidad}</td>
                                    <td><fmt:formatNumber value="${hardware.precio}" type="currency"/></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/servletControlador?accion=editar&idHardware=${hardware.idHardware}" class="btn btn-secondary">
                                            Editar
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-3">
                <div class="card text-center bg-danger text-white mb-3">
                    <div class="card-body">
                        <h3>Cantidad de items</h3>
                        <h4 class="display-4">${cantProductos}</h4>
                    </div>
                </div>
                <div class="card text-center bg-success text-white mb-3">
                    <div class="card-body">
                        <h3>Total a pagar</h3>
                        <h4 class="display-4"><fmt:formatNumber value="${totalProductos}" type="currency"/></h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="/WEB-INF/pages/functions/agregarProducto.jsp"/>