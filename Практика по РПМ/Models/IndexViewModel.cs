using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Практика_по_РПМ.Models
{
    public class IndexViewModel
    {
        public IEnumerable<User> Users { get; set; }
        public PageViewModel PageViewModel { get; set; }
        public SortViewModel SortViewModel { get; set; }
        public SortViewImage SortViewImage { get; set; }
        public SortViewPost SortViewPost { get; set; }
        public FilterViewModel FilterViewModel { get; set; }
        public FilterViewImage FilterViewImage { get; set; }
        public FilterViewPost FilterViewPost { get; set; } 
        public IEnumerable<Post>  Posts { get; set; }
        public IEnumerable<Image>   Images { get; set; }
        public IEnumerable<PostImage>   PostImage { get; set; }

    }
}
