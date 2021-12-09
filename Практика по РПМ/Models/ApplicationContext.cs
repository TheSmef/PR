using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

namespace Практика_по_РПМ.Models
{
    public class ApplicationContext : DbContext
    {
        public DbSet<User> Users { get; set; }
        public DbSet<Post> Post { get; set; }
        public DbSet<Image> Image { get; set; }
        public DbSet<PostImage> PostImage { get; set; }
        public ApplicationContext(DbContextOptions<ApplicationContext> options) : base (options)
        {
            Database.EnsureCreated();
        }
    }
}
