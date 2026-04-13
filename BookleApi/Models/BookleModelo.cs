using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace BookleApi.Models
{
    public class Actividad
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string? Id { get; set; }
        public string Titulo { get; set; } = string.Empty;
        public string Descripcion { get; set; } = string.Empty;
        public string Profesor { get; set; } = string.Empty;
        public string Email { get; set; } = string.Empty;
        public List<DiaActividad> Agenda { get; set; } = [];

    }
    public class DiaActividad
    {
        public DateOnly Fecha { get; set; }
        public List<Turno> Turnos { get; set; } = [];
    }
    public class Turno    {
        public string Horario { get; set; } = string.Empty;
        public Reserva? Reserva { get; set; }
    }

    public class Reserva
    {
        public string Alumno { get; set; } = string.Empty;
        public string Email { get; set; } = string.Empty;
    }
}