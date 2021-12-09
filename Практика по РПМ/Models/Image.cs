using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Практика_по_РПМ.Models
{
    public class Image
    {
        [Key]
        public int Id { get; set; }
        public string Source { get; set; }

    }
    public enum SortStateImage
    {
        IdAsc,
        IdDesc
    }
}
