using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using static Практика_по_РПМ.Models.Image;
using static Практика_по_РПМ.Models.Post;

namespace Практика_по_РПМ.Models
{
    public class SortViewModel
    {
        public SortState IdSort { get; private set; }
        public SortState LoginSort { get; private set; }
        public SortState EmailSort { get; private set; }
        public SortState Current { get; private set; }

        public SortViewModel(SortState sortOrder)
        {
            IdSort = sortOrder == SortState.IdAsc ?
             SortState.IdDesc : SortState.IdAsc;
            EmailSort = sortOrder == SortState.EmailAsc ?
            SortState.EmailDesc : SortState.EmailAsc;
            LoginSort = sortOrder == SortState.LoginAsc ?
             SortState.LoginDesc : SortState.LoginAsc;
            Current = sortOrder;
        }
    }

    public class SortViewImage
    {
        public SortStateImage IdSort { get; private set; }
        public SortStateImage Current { get; private set; }

        public SortViewImage(SortStateImage sortOrder)
        {
            IdSort = sortOrder == SortStateImage.IdAsc ?
             SortStateImage.IdDesc : SortStateImage.IdAsc;
            Current = sortOrder;
        }
    }
    public class SortViewPost
    {
        public SortStatePost IdSort { get; private set; }
        public SortStatePost TitleSort { get; private set; }
        public SortStatePost Current { get; private set; }

        public SortViewPost(SortStatePost sortOrder)
        {
            IdSort = sortOrder == SortStatePost.IdAsc ?
             SortStatePost.IdDesc : SortStatePost.IdAsc;
            TitleSort = sortOrder == SortStatePost.TitleAsc ?
             SortStatePost.TitleDecs : SortStatePost.TitleAsc;
        }
    }
}
