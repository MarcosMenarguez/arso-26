namespace BookleMinApi.Common;
public record Resultado(bool Success, string? Message)
{
    public static Resultado Ok() => new(true, null);
    public static Resultado Error(string msg) => new(false, msg);
    public static Resultado NotFound(string msg) => new(false, msg);
    public static Resultado Conflict(string msg) => new(false, msg);
}