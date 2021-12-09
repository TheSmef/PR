using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace Практика_по_РПМ.Models
{
    public class PostImage
    {
        [Key]
        public int Id { get; set; }
        [ForeignKey("PostID1")]
        public virtual Post Post_Id { get; set; }
        [ForeignKey("ImageID1")]
        public virtual Image Image_Id { get; set; }
    }
}
