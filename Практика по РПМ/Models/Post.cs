using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace Практика_по_РПМ.Models
{
    public class Post
    {
        [Key]
        public int id { get; set; }
        public string Title { get; set; }
        public string Message { get; set; }
        public DateTime Date { get; set; }
        [ForeignKey("UserID")]
        public virtual User User { get; set; }

        public enum SortStatePost
        {
            IdAsc,
            IdDesc,
            TitleAsc,
            TitleDecs
        }
    }
}
