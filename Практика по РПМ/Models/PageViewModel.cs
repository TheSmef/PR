using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Практика_по_РПМ.Models
{
    public class PageViewModel
    {
        public int PageNumber { get; private set; }
        public int TotalPage { get; private set; }
        public PageViewModel(int count, int pagenumber, int pagesize)
        {
            PageNumber = pagenumber;
            TotalPage = (int)Math.Ceiling(count / (double)pagesize);
        }
        public bool HasPreviousPage
        {
            get { return (PageNumber) > 1; }
        }
        public bool HasNextPage
        {
            get { return (PageNumber) < TotalPage; }
        }
    }
}
