using BookleApi.Models;
using BookleApi.Repositories;
using BookleApi.DTOs;
using BookleApi.Common;

namespace BookleApi.Services;


public class ServicioBookle : IServicioBookle
{
    private readonly IRepositorio<Actividad, string> _repositorio;

    public ServicioBookle(IRepositorio<Actividad, string> repositorio)   {
        _repositorio = repositorio;
    }

    public async Task<string> CreateAsync(Actividad actividad)
    {
        return await _repositorio.AddAsync(actividad);
    }
    public async Task<Resultado> UpdateAsync(Actividad actividad)
    {
        await _repositorio.UpdateAsync(actividad);
        return Resultado.Ok();
    }

    public async Task<Actividad?> GetAsync(String id)
    {
        return await _repositorio.GetByIdAsync(id);
    }

    public async Task<Resultado> RemoveAsync(String id)
    {
        var actividad = await _repositorio.GetByIdAsync(id);
        if(actividad == null)
        {
            return Resultado.NotFound("Actividad no encontrada");
        }
        await _repositorio.DeleteAsync(actividad);
        return Resultado.Ok();
    }

    public async Task<Resultado> ReservarAsync(String id, DateOnly fecha, int indice, String alumno, String email)
    {
        if (indice < 1)
            return Resultado.Error("Los índices comienzan en 1");

        if (alumno == null || alumno == "")
            return Resultado.Error("El nombre del alumno no debe ser vacío");

        // email es opcional

        var actividad = await _repositorio.GetByIdAsync(id);

        if (actividad == null)
            return Resultado.NotFound("Actividad no encontrada.");

        var diaActividad = actividad.Agenda.Find(dia => dia.Fecha == fecha);
        if(diaActividad == null)
        {
            return Resultado.Error("No hay actividad ese día");
        }

        if (indice > diaActividad.Turnos.Count)
            return Resultado.Error("No existe el turno " + indice + " para la fecha " + fecha);

        var turno = diaActividad.Turnos[indice - 1];

        if (turno.Reserva != null)
            return Resultado.Conflict("Ese turno ya está reservado");

        turno.Reserva = new Reserva()
        {
            Alumno = alumno,
            Email = email
        };

        await _repositorio.UpdateAsync(actividad);

        return Resultado.Ok();
    }

    public async Task<List<ActividadResumen>> GetResumenesAsync()
    {
        var actividades = await _repositorio.GetAllAsync();

        return actividades.Select(a => new ActividadResumen(a.Id, a.Titulo, a.Profesor)).ToList();
    }

}
