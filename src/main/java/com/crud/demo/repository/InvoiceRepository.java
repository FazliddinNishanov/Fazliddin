package com.crud.demo.repository;

import com.crud.demo.dto.InvoiceDto;
import com.crud.demo.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    @Query(nativeQuery = true,value = "SELECT * FROM Invoice i where i.due < current_date")
    List<Invoice> findAllByIssuedAndDue();

    @Query(nativeQuery = true, value = "SELECT invoice.id, invoice.issued, invoice.ord_id, orders.date as orderDate FROM invoice LEFT JOIN orders ON invoice.ord_id = orders.id WHERE invoice.issued < orders.date")
    List<Object> findAllByBeforeOrder();

    @Query(nativeQuery = true, value = "SELECT * FROM orders WHERE orders.date < '2016-09-06'")
    List<Object> findAllByBeforeSeptember();

    @Query(nativeQuery = true, value = "select c.* from customer c, (select c.id as id from customer c,orders o where o.cust_id = c.id and extract(year from date) = '2016') a where a.id != c.id")
    List<Object> findAllByNotGetCertainYear();

    @Query(nativeQuery = true, value = "SELECT a.* FROM (SELECT c.id AS id , c.name AS name, (select max(o.date) from orders o where o.cust_id=c.id) AS max_dat_val from customer c) a where a.max_dat_val is not null")
    List<Object> findAllByCustomerLastOrder();

    @Query(nativeQuery = true, value = "select inv_id, amount, count(*) from payment group by  inv_id, amount having count(*)>1")
    List<Object> findAllByInvoicesOverpaid();

    @Query(nativeQuery = true, value = "select pr_id, sum(quantity) from detail group by pr_id having sum(quantity)>10")
    List<Object> findAllByQuantity();

    @Query(nativeQuery = true, value = "select a.country, count(*) from customer a, orders b where a.id = b.cust_id and to_char(b.date,'yyyy')='2016' group by a.country having count(*)>0")
    List<Object> findAllByCountrySelected();

    @Query(nativeQuery = true, value = "SELECT O.ID,O.DATE, SUM(P.price*D.quantity) FROM (SELECT O.id AS ID, O.date DATE,(SELECT I.ORD_ID FROM invoice I WHERE O.id = I.ord_id)AS II FROM orders O) O,detail D, product P WHERE II IS NULL  AND D.ord_id = O.ID AND P.id = D.pr_id GROUP BY O.ID,O.DATE")
    List<Object> findAllBy10();
}
