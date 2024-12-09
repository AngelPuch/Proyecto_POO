package org.project.salesystem.customer.dao;

import org.project.salesystem.customer.model.SaleDetail;
import org.project.salesystem.database.dao.DAO;

import java.util.List;

/**
 * Interface that defines specific operations related to sale details (SaleDetail).
 * Extends the base {@link DAO} interface to leverage standard CRUD operations.
 */
public interface SaleDetailDAO extends DAO<SaleDetail> {

    /**
     * Retrieves a list of SaleDetail records associated with a specific Sale ID.
     *
     * @param saleId the ID of the Sale whose details are to be retrieved.
     * @return a List of SaleDetail objects.
     */
    List<SaleDetail> getSaleDetailsBySaleId(int saleId);

    List<SaleDetail> getSaleDetailByCustomerId(int customerId);

    /**
     * Checks if there are sale details associated with a specific product.
     * This is useful for validating whether a product can be safely deleted from the system.
     *
     * @param productId the ID of the product to check for associations.
     * @return {@code true} if there are sale details associated with the product;
     *         {@code false} otherwise.
     */
    boolean hasSaleDetailsAssociatedWithProducts(int productId);
}
