namespace BookleMinApi.DTOs;
public record ActividadRequest(
    string Titulo,
    string Descripcion,
    string Profesor,
    string Email,
    List<DiaRequest> Dias);

public record DiaRequest(DateOnly Fecha, List<string> Horarios);