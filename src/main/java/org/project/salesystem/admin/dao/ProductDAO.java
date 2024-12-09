package org.project.salesystem.admin.dao;

import org.project.salesystem.admin.model.Product;
import org.project.salesystem.database.dao.DAO;

/**
 * Interface for managing Product-related data access operations.
 * Extends the generic DAO interface for common CRUD operations.
 */
public interface ProductDAO extends DAO<Product> {

    /**
     * Checks if a supplier is associated with any products.
     * This method is used to determine whether a supplier can be safely deleted.
     *
     * @param supplierId the ID of the supplier to check.
     * @return {@code true} if the supplier is associated with at least one product,
     *         {@code false} otherwise.
     */
    boolean hasProductsAssociatedWithSupplier (int supplierId);

    /**
     * Checks if a category is associated with any products.
     * This method is used to determine whether a category is referenced by any products
     * and if it can be safely deleted.
     *
     * @param categoryId the ID of the category to check.
     * @return {@code true} if the category is associated with at least one product,
     *         {@code false} otherwise.
     */
    boolean hasProductsAssociatedWithCategory(int categoryId);

    /**
     * Updates the stock of a product after a sale.
     *
     * @param productId The ID of the product whose stock will be updated.
     * @param quantitySold The quantity sold to be deducted from the stock.
     */
    void updateProductStock(int productId, int quantitySold);
}
