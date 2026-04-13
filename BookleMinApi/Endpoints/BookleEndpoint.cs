using BookleMinApi.Services;
using BookleMinApi.Models;
using BookleMinApi.DTOs;
namespace BookleMinApi.Endpoints;

public static class ActividadesEndpoints
{
    public static void MapActividadesEndpoints(this WebApplication app)
    {
        var group = app.MapGroup("/api/actividades");

        group.MapGet("/", Get);
        group.MapGet("/{id}", GetById).WithName("GetActividad");
        group.MapPost("/", Create);
        group.MapPut("/{id}", Update);
        group.MapDelete("/{id}", Delete);
        group.MapPost("/{id}/agenda/{fecha}/turno/{indice}/reserva", Reservar);
    }

    static async Task<IResult> Get(IServicioBookle servicio) =>
       Results.Ok(await servicio.GetResumenesAsync());

    static async Task<IResult> GetById(string id, IServicioBookle service)
    {
        var actividad = await service.GetAsync(id);
        return actividad == null ? Results.NotFound() : Results.Ok(actividad);
    }

    private static async Task<IResult> Create(Actividad actividad, IServicioBookle servicio)
    {
        var id = await servicio.CreateAsync(actividad);

        return Results.CreatedAtRoute("GetActividad", new { id }, actividad);
    }

    private static async Task<IResult> Update(string id, Actividad actividad, IServicioBookle servicio)
    {
        var existente = await servicio.GetAsync(id);

        if (existente == null)
            return Results.NotFound();

        actividad.Id = id;

        var result = await servicio.UpdateAsync(actividad);

        if (!result.Success)
            return Results.BadRequest(result.Message);

        return Results.NoContent();
    }

    static async Task<IResult> Delete(string id, IServicioBookle servicio)
    {
        var result = await servicio.RemoveAsync(id);

        if (!result.Success)
            return Results.NotFound(result.Message);

        return Results.NoContent();
    }

    static async Task<IResult> Reservar(
        string id, DateOnly fecha, int indice, ReservaRequest datosReserva,
        IServicioBookle servicio)
    {
        var result = await servicio.ReservarAsync(id, fecha, indice, datosReserva.Alumno, datosReserva.Email);

        if (!result.Success)
            return Results.BadRequest(result.Message);

        return Results.NoContent();
    }
}