package br.com.uniamerica.pizzariaback.repository;

import br.com.uniamerica.pizzariaback.entity.Pedido;
import br.com.uniamerica.pizzariaback.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface PedidoRep extends JpaRepository <Pedido, Long> {

    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.dataPedido = :data")
    Long pedidosPorData(@Param("data") LocalDate data);

    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.pagamentoCartao = true AND p.dataPedido = :data")
    Long totalPedidosCartao(@Param("data") LocalDate data);

    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.pagamentoDinheiro = true AND p.dataPedido = :data")
    Long totalPedidosDinheiro(@Param("data") LocalDate data);

    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.delivery = true AND p.dataPedido = :data")
    Long pedidosDelivery(@Param("data") LocalDate data);

    List<Pedido> findByDelivery(boolean delivery);
    List<Pedido> findByStatus(Status status);

    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.delivery = false AND p.dataPedido = :data")
    Long totalPedidosBalcao(@Param("data") LocalDate data);

    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.status = 'CANCELADO' AND p.dataPedido = :data")
    Long totalCancelados(@Param("data") LocalDate data);

    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.status = 'ENTREGUE' AND p.dataPedido = :data")
    Long totalPagos(@Param("data") LocalDate data);

}
