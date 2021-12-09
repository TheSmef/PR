using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Практика_по_РПМ.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.AspNetCore.Http;
using static Практика_по_РПМ.Models.Post;
using Microsoft.AspNetCore.Hosting;

namespace Практика_по_РПМ.Controllers 
{
    public class HomeController : Controller
    {
        IHttpContextAccessor _httpContextAccessor;
        private ApplicationContext db;
        private IWebHostEnvironment _app;
        public HomeController(ApplicationContext context, IHttpContextAccessor http, IWebHostEnvironment app)
        {
            db = context;
            _httpContextAccessor = http;
            _app = app;
        }
        public async Task<IActionResult> Post(int? id, string title, DateTime date, int page = 1, SortStatePost sortOrder = SortStatePost.IdAsc)
        {
            IQueryable<Post> users = db.Post;
            if (id != null && id > 0)
            {
                users = users.Where(p => p.id == id);
            }
            if (!string.IsNullOrEmpty(title))
            {
                users = users.Where(p => p.Title.Contains(title));
            }
            switch (sortOrder)
            {
                case SortStatePost.IdAsc:
                    {
                        users = users.OrderBy(m => m.id);
                        break;
                    }
                case SortStatePost.IdDesc:
                    {
                        users = users.OrderByDescending(m => m.id);
                        break;
                    }
                case SortStatePost.TitleAsc:
                    {
                        users = users.OrderBy(m => m.Title);
                        break;
                    }
                case SortStatePost.TitleDecs:
                    {
                        users = users.OrderByDescending(m => m.Title);
                        break;
                    }
                default:
                    {
                        users = users.OrderBy(m => m.id);
                        break;
                    }

            }
            var count = await users.CountAsync();
            int pagesize = 4;
            var item = await users.Skip((page - 1) * pagesize).Take(pagesize).Include(b => b.User).ToListAsync();
            IndexViewModel viewModel = new IndexViewModel
            {
                PageViewModel = new PageViewModel(count, page, pagesize),
                SortViewPost = new SortViewPost(sortOrder),
                FilterViewPost = new FilterViewPost(id, title),
                Posts = item
            };
            return View(viewModel);
        }
        public async Task<IActionResult> Index(int? id, string login, string email, int page = 1, SortState sortOrder = SortState.IdAsc)
        {
            IQueryable<User> users = db.Users;
            if (id != null && id > 0)
            {
                users = users.Where(p => p.Id == id);
            }
            if (!string.IsNullOrEmpty(login))
            {
                users = users.Where(p => p.Login.Contains(login));
            }
            switch (sortOrder)
            {
                case SortState.IdAsc:
                    {
                        users = users.OrderBy(m => m.Id);
                        break;
                    }
                case SortState.IdDesc:
                    {
                        users = users.OrderByDescending(m => m.Id);
                        break;
                    }
                case SortState.EmailAsc:
                    {
                        users = users.OrderBy(m => m.Email);
                        break;
                    }
                case SortState.EmailDesc:
                    {
                        users = users.OrderByDescending(m => m.Email);
                        break;
                    }
                case SortState.LoginAsc:
                    {
                        users = users.OrderBy(m => m.Login);
                        break;
                    }
                case SortState.LoginDesc:
                    {
                        users = users.OrderByDescending(m => m.Login);
                        break;
                    }
                default:
                    {
                        users = users.OrderBy(m => m.Id);
                        break;
                    }

            }
            var count = await users.CountAsync();
            int pagesize = 4;
            var item = await users.Skip((page - 1) * pagesize).Take(pagesize).Include(b => b.id_Image).ToListAsync();
            IndexViewModel viewModel = new IndexViewModel
            {
                PageViewModel = new PageViewModel(count, page, pagesize),
                SortViewModel = new SortViewModel(sortOrder),
                FilterViewModel = new FilterViewModel(id, email, login),
                Users = item
            };
            return View(viewModel);
        }

        public async Task<IActionResult> Image(int? id, int page = 1, SortStateImage sortOrder = SortStateImage.IdAsc)
        {
            IQueryable<Image> users = db.Image;
            if (id != null && id > 0)
            {
                users = users.Where(p => p.Id == id);
            }
            switch (sortOrder)
            {
                case SortStateImage.IdAsc:
                    {
                        users = users.OrderBy(m => m.Id);
                        break;
                    }
                case SortStateImage.IdDesc:
                    {
                        users = users.OrderByDescending(m => m.Id);
                        break;
                    }
                default:
                    {
                        users = users.OrderBy(m => m.Id);
                        break;
                    }

            }
            var count = await users.CountAsync();
            int pagesize = 4;
            var item = await users.Skip((page-1) * pagesize).Take(pagesize).ToListAsync();
            IndexViewModel viewModel = new IndexViewModel
            {
                PageViewModel = new PageViewModel(count, page, pagesize),
                SortViewImage = new SortViewImage(sortOrder),
                FilterViewImage = new FilterViewImage(id),
                Images = item
            };
            return View(viewModel);
        }

        public IActionResult Create()
        {
            return View();
        }

        [HttpPost]

        public async Task<IActionResult> Create(User user)
        {
            db.Users.Add(user);
            await db.SaveChangesAsync();
            return RedirectToAction("Index");
        }


        [HttpGet]
        [ActionName("Delete")]
        public async Task<IActionResult> ConfirmDelete(int? id )
        {
            if (id != null)
            {
                User user = await db.Users.FirstOrDefaultAsync(predicate => predicate.Id == id);
                if (user != null)
                {
                    return View(user);
                }
            }
            return NotFound();

        }
        [HttpPost]
        public async Task<IActionResult> Delete(int? id)
        {
            if (id != null)
            {
                User user = await db.Users.FirstOrDefaultAsync(predicate => predicate.Id == id);
                if (user != null)
                {
                    db.Users.Remove(user);
                    await db.SaveChangesAsync();
                    return RedirectToAction("Index");
                }
            }
            return NotFound();
        }
        public async Task<IActionResult> deleteImage(int? id)
        {
            if (id != null)
            {
                Image user = await db.Image.FirstOrDefaultAsync(predicate => predicate.Id == id);
                if (user != null)
                {
                    db.Image.Remove(user);
                    System.IO.File.Delete(_app.WebRootPath+ user.Source);
                    await db.SaveChangesAsync();
                    return RedirectToAction("Index");
                }
            }
            return NotFound();
        }

        public async Task<IActionResult> deletePost(int? id)
        {
            if (id != null)
            {
                Post user = await db.Post.FirstOrDefaultAsync(predicate => predicate.id == id);
                var postsImage = db.PostImage.Where(p => p.Post_Id.id == user.id);
                if (user != null)
                {
                    db.PostImage.RemoveRange(postsImage);
                    db.Post.Remove(user);
                    await db.SaveChangesAsync();
                    return RedirectToAction("Index");
                }
            }
            return NotFound();
        }

        public async Task<IActionResult> Edit(int? id)
        {
            if (id != null)
            {
                User user = await db.Users.FirstOrDefaultAsync(predicate => predicate.Id == id);
                if (user != null)
                {
                    return View(user);
                }
            }
            return NotFound();

        }
        [HttpPost]

        public async Task<IActionResult> Edit(User user)
        {
            db.Users.Update(user);
            await db.SaveChangesAsync();
            return RedirectToAction("Index");
        }

        public async Task<IActionResult> Details(int? id)
        {
            if (id != null)
            {
                User user = await db.Users.FirstOrDefaultAsync(predicate => predicate.Id == id);
                if (user != null)
                {
                    return View(user);
                }
            }
            return NotFound();

        }

        public async Task<IActionResult> DetailsPost(int? id)
        {
            if (id != null)
            {
                Post user = await db.Post.FirstOrDefaultAsync(predicate => predicate.id == id);
                if (user != null)
                {
                    return View(user);
                }
            }
            return NotFound();

        }
    }

}
