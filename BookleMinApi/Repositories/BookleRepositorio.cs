
using MongoDB.Driver;
using BookleMinApi.Models;

namespace BookleMinApi.Repositories
{
    public class RepositorioActividadesMongoDB : IRepositorio<Actividad, string>
    {
        private readonly IMongoCollection<Actividad> _actividades;

        public RepositorioActividadesMongoDB(IMongoClient client, IConfiguration config)
        {
            var database = client.GetDatabase(config["MongoDB:Database"]);
            _actividades = database.GetCollection<Actividad>(
                config["MongoDB:Collection"] ?? "actividades.net");            
        }

        public async Task<string> AddAsync(Actividad entity)
        {
            await _actividades.InsertOneAsync(entity);
            return entity.Id;
        }
        public async Task UpdateAsync(Actividad entity)
        {
            await _actividades.ReplaceOneAsync(a => a.Id == entity.Id, entity);
        }
        public async Task DeleteAsync(Actividad entity)
        {
            await _actividades.DeleteOneAsync(a => a.Id == entity.Id);
        }
        public async Task<List<Actividad>> GetAllAsync() => 
            await _actividades.Find(_ => true).ToListAsync();
        
        public async Task<Actividad?> GetByIdAsync(string id) =>
            await _actividades.Find(a => a.Id == id).FirstOrDefaultAsync();

        public async Task<List<string>> GetIdsAsync()
        {
            //IMongoCollection no tiene Select(), hacemos Find para obtener un cursor
            var todas = await _actividades.Find(_ => true).ToListAsync();
            return todas.Select(a => a.Id).ToList();
        }
       
    }
}