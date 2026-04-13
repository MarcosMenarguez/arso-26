using Microsoft.AspNetCore.Mvc;
using BookleApi.Services;
using BookleApi.DTOs;
using BookleApi.Models;

namespace BookleApi.Controllers;

[Route("api/actividades")]
[ApiController]
public class ActividadesController : ControllerBase
{
    private readonly IServicioBookle _servicio;

    public ActividadesController(IServicioBookle servicio)
    {
        _servicio = servicio;
    }

    [HttpGet]
    public async Task<ActionResult<List<ActividadResumen>>> Get()
    {
        var resumenes = await _servicio.GetResumenesAsync();
        return Ok(resumenes);
    }


    [HttpGet("{id}", Name = "GetActividad")]
    public async Task<ActionResult<Actividad>> Get(string id)
    {
        var actividad = await _servicio.GetAsync(id);

        if (actividad == null)
        {
            return NotFound();
        }
        return Ok(actividad);
    }

    [HttpPost]
    public async Task<ActionResult<Actividad>> Create(Actividad actividad)
    {
        var id = await _servicio.CreateAsync(actividad);
        return CreatedAtRoute("GetActividad", new { id }, actividad);
    }

    [HttpPut("{id}")]
    public async Task<IActionResult> Update(string id, Actividad actividad)
    {
        var entidad = await _servicio.GetAsync(id);

        if (entidad == null)
        {
            return NotFound();
        }
        actividad.Id = id;

        var result = await _servicio.UpdateAsync(actividad);
        if (!result.Success)
            return BadRequest(result.Message);

        return NoContent();
    }

    [HttpDelete("{id}")]
    public async Task<IActionResult> DeleteAsync(string id)
    {
        var result = await _servicio.RemoveAsync(id);

        if (!result.Success)
            return NotFound(result.Message);

        return NoContent();
    }

    [HttpPost("{id}/agenda/{fecha}/turno/{indice}/reserva")]
    public async Task<IActionResult> Reservar(string id, DateOnly fecha, int indice, [FromForm] string alumno,
     [FromForm] string email)
    {
        var result = await _servicio.ReservarAsync(id, fecha, indice, alumno, email);

        if (!result.Success)
        {
            return BadRequest(result.Message);
        }
        return NoContent();
    }
}
