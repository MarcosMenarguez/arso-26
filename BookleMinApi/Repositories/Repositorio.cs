namespace BookleMinApi.Repositories
{
    public interface IRepositorio<T, K>
    {
        Task<K> AddAsync(T entity);
        Task UpdateAsync(T entity);
        Task DeleteAsync(T entity);
        Task<T?> GetByIdAsync(K id);
        Task<List<T>> GetAllAsync();
        Task<List<K>> GetIdsAsync();
    }

}