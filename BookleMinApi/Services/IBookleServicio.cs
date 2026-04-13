using BookleMinApi.Models;
using BookleMinApi.DTOs;
using BookleMinApi.Common;

namespace BookleMinApi.Services;

public interface IServicioBookle
{
    Task<string> CreateAsync(Actividad actividad);
    Task<Resultado> UpdateAsync(Actividad actividad);
    Task<Resultado> RemoveAsync(string id);
    Task<Resultado> ReservarAsync(string idActividad, DateOnly fecha, int indice, string alumno, string email);

    Task<Actividad?> GetAsync(string id);    
    Task<List<ActividadResumen>> GetResumenesAsync();
}
