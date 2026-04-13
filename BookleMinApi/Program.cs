using MongoDB.Driver;
using BookleMinApi.Repositories;
using BookleMinApi.Models;
using BookleMinApi.Services;
using BookleMinApi.Endpoints;
using BookleMinApi.Middleware;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddOpenApiDocument(config =>
{
    config.DocumentName = "BookleMinApi";
    config.Title = "BookleMinApi v1";
    config.Version = "v1";
});

builder.Services.AddSingleton<IMongoClient>(
    new MongoClient(builder.Configuration["MongoDB:Uri"]));
builder.Services.AddScoped<IRepositorio<Actividad, string>, RepositorioActividadesMongoDB>();
builder.Services.AddScoped<IServicioBookle, ServicioBookle>();

var app = builder.Build();

if (app.Environment.IsDevelopment())
{
    app.UseOpenApi();
    app.UseSwaggerUi(config =>
    {
        config.DocumentTitle = "BookleMinApi";
        config.Path = "/swagger";
        config.DocumentPath = "/swagger/{documentName}/swagger.json";
        config.DocExpansion = "list";
    });
}

//app.MapGet("/", () => "Hello World!");
app.UseMiddleware<ExceptionMiddleware>();
app.MapActividadesEndpoints();
app.Run();
