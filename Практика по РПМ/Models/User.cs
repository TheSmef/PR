using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;
using static System.Net.Mime.MediaTypeNames;

namespace Практика_по_РПМ.Models
{
    public class User
    {
        [Key]
        public int Id { get; set; }
        public string Email { get; set; }
        public string Login { get; set; }
        public string Password { get; set; }
        public int Role_Id { get; set; }
        public DateTime birth_date { get; set; }
        [ForeignKey("ImageID")]
        public virtual Image id_Image { get; set; }
        public string Description { get; set; }
    }

    public enum SortState
    {
        IdAsc,
        IdDesc,
        EmailAsc,
        EmailDesc,
        LoginAsc,
        LoginDesc
    }
    public class LogUser
    {
        public string Login { get; set; }
        public string Password { get; set; }
    }
}
