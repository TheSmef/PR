using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Практика_по_РПМ.Models
{
    public class FilterViewModel
    {
        public int? SelectId { get; private set; }
        public string SelectLogin { get; private set; }
        public string SelectEmail { get; private set; }
        public FilterViewModel(int? id, string email, string login)
        {
            SelectId = id;
            SelectLogin = login;
            SelectEmail = email;
        }
    }
    public class FilterViewImage
    {
        public int? SelectId { get; private set; }
        public FilterViewImage(int? id)
        {
            SelectId = id;
        }
    }
    public class FilterViewPost
    {
        public int? SelectId { get; private set; }
        public string SelectTitle { get; private set; }
        public FilterViewPost(int? id, string title)
        {
            SelectId = id;
            SelectTitle = title;
        }
    }
}
