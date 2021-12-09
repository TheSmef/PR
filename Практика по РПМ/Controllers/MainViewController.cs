using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Практика_по_РПМ.Models;
using System.Web;
using System.IO;
using Microsoft.AspNetCore.Hosting;

namespace Практика_по_РПМ.Controllers
{

    public class MainViewController : Controller
    {
        IHttpContextAccessor _httpContextAccessor;
        private ApplicationContext db;
        private IWebHostEnvironment _app;
        public MainViewController(ApplicationContext context, IHttpContextAccessor httpContextAccessor, IWebHostEnvironment app)
        {
            db = context;
            _httpContextAccessor = httpContextAccessor;
            _app = app;
        }


        public async Task<IActionResult> Autorization()
        {
            string cookieLogin = _httpContextAccessor.HttpContext.Request.Cookies["Login"];
            string cookiePass = _httpContextAccessor.HttpContext.Request.Cookies["Pass"];
            ViewData["Login"] = cookieLogin;
            ViewData["Password"] = cookiePass;


            return View();
        }
        public async Task<IActionResult> MainView()
        {
            int id =Int32.Parse( HttpContext.Session.GetString("IDUser"));
            var user = db.Users.Include(b => b.id_Image).Where(predicate => predicate.Id == id);
            var post = db.Post.Include(b => b.User).Where(predicate => predicate.User.Id == id);
            var posts = db.PostImage.Include(b => b.Image_Id).Include(b => b.Post_Id).Where(predicate => predicate.Post_Id.User.Id == id);
            IndexViewModel viewModel = new IndexViewModel
            {
                Users = user,
                PostImage = posts,
                Posts = post,
            };
            return View(viewModel);
            
        }

        public async Task<IActionResult> DataChange()
        {
            int id = Int32.Parse(HttpContext.Session.GetString("IDUser"));
            User user = await db.Users.Include(b => b.id_Image).FirstOrDefaultAsync(predicate => predicate.Id == id);
            return View(user);
        }

        public async Task<IActionResult> Edit(User user, IFormFile file, DateTime datecringe)
        {
            if (user != null)
            {
                user.birth_date = datecringe;
                string path;
                Image newfile;
                if (file != null)
                {
                    path = file.FileName;
                    int i = 0;
                    while (System.IO.File.Exists(_app.WebRootPath + "/Images/" + path))
                    {
                        i++;
                        try
                        {
                            if (i == 1)
                                path = Path.GetFileNameWithoutExtension(path) + Convert.ToString("(" + i + ")") + ".jpg";
                            else
                            {
                                path = Path.GetFileNameWithoutExtension(path).Substring(0, Path.GetFileNameWithoutExtension(path).Length - 3) + Convert.ToString("(" + i + ")") + ".jpg";
                            }
                        }
                        catch
                        {

                        }


                    }
                    using (var fileStream = new FileStream(_app.WebRootPath + "/Images/" + path, FileMode.Create))
                    {
                        await file.CopyToAsync(fileStream);
                    }
                    newfile = new Image
                    {
                        Source = "/Images/" + path,
                    };
                    db.Image.Add(newfile);
                    await db.SaveChangesAsync();
                    Image image = await db.Image.FirstOrDefaultAsync(p => p.Source == newfile.Source);
                    user.id_Image = image;
                }
                db.Users.Update(user);
                await db.SaveChangesAsync();
            }
            return RedirectToAction("MainView");
        }

        public async Task<IActionResult> Login(LogUser userLog)
        {
            try
            {
                if (userLog != null)
                {
                    User user = await db.Users.FirstOrDefaultAsync(predicate => predicate.Login == userLog.Login);
                    
                    if (user != null && userLog.Password == user.Password)
                    {
                        CookieOptions cookieLogin = new CookieOptions();
                        cookieLogin.Expires = DateTime.Now.AddYears(2);
                        Response.Cookies.Append("Login", user.Login, cookieLogin);
                        CookieOptions cookiePass = new CookieOptions();
                        cookiePass.Expires = DateTime.Now.AddDays(7);
                        Response.Cookies.Append("Pass", user.Password, cookiePass);
                        HttpContext.Session.SetString("IDUser", user.Id.ToString());
                        if (user.Role_Id == 0)
                            return RedirectToAction("MainView");
                        else
                            return RedirectToAction("Index", "Home");
                    }
                    else
                    {
                        user = await db.Users.FirstOrDefaultAsync(predicate => predicate.Email == userLog.Login);
                        if (user != null && userLog.Password == user.Password)
                        {
                            CookieOptions cookieLogin = new CookieOptions();
                            cookieLogin.Expires = DateTime.Now.AddYears(2);
                            Response.Cookies.Append("Login", user.Login, cookieLogin);
                            CookieOptions cookiePass = new CookieOptions();
                            cookiePass.Expires = DateTime.Now.AddDays(7);
                            Response.Cookies.Append("Pass", user.Password, cookiePass);
                            HttpContext.Session.SetString("IDUser",user.Id.ToString());
                            if (user.Role_Id == 0)
                                return RedirectToAction("MainView");
                            else
                                return RedirectToAction("Index", "Home");
                        }
                    }

                    return RedirectToAction("AutWrong");
                }
            }
            catch { }
            return RedirectToAction("AutWrong");

        }
        
        public async Task<IActionResult> PostInfo(int? id)
        {
            if(id != null)
            {
                var post = db.Post.Include(b => b.User).Where(predicate => predicate.id == id);
                var posts = db.PostImage.Include(b => b.Image_Id).Include(b => b.Post_Id).Where(predicate => predicate.Post_Id.id == id);
                IndexViewModel viewModel = new IndexViewModel
                {
                    PostImage = posts,
                    Posts = post,
                };
                return View(viewModel);
            }
            return RedirectToAction("ListOfUsers");
        }
        public async Task<IActionResult> Registration()
        {
            return View();
        }
        public async Task<IActionResult> UserPage(int ?id)
        {
            if (id != null)
            {
                if (id == Int32.Parse(HttpContext.Session.GetString("IDUser")))
                {
                    return RedirectToAction("MainView");
                }
                var user = db.Users.Include(b => b.id_Image).Where(predicate => predicate.Id == id);
                var post = db.Post.Include(b => b.User).Where(predicate => predicate.User.Id == id);
                var posts = db.PostImage.Include(b => b.Image_Id).Include(b => b.Post_Id).Where(predicate => predicate.Post_Id.User.Id == id);
                IndexViewModel viewModel = new IndexViewModel
                {
                    Users = user,
                    PostImage = posts,
                    Posts = post,
                };
                return View(viewModel);
            }
            return RedirectToAction("ListOfUsers");
            
        }
        public async Task<IActionResult> AutWrong()
        {
            return View();
        }
        public async Task<IActionResult> Post(Post post, IFormFile[] files)
        {
            if (post != null)
            {
                DateTime timePost = DateTime.Now;
                post.Date = timePost;
                post.User = await db.Users.FirstOrDefaultAsync(p => p.Id.ToString() == HttpContext.Session.GetString("IDUser"));
                db.Post.Add(post);
                await db.SaveChangesAsync();
                Image newfile;
                String path;
                Post postId = await db.Post.FirstOrDefaultAsync(p => p.User.Id.ToString() == HttpContext.Session.GetString("IDUser") && p.Date == timePost);
                if (files != null)
                {
                    foreach (IFormFile file in files)
                    {
                        path = file.FileName;
                        int i = 0;
                        while (System.IO.File.Exists(_app.WebRootPath + "/Images/" + path))
                        {
                            i++;
                            try
                            {
                                if (i == 1)
                                    path = Path.GetFileNameWithoutExtension(path) + Convert.ToString("(" + i + ")") + ".jpg";
                                else
                                {
                                    path = Path.GetFileNameWithoutExtension(path).Substring(0, Path.GetFileNameWithoutExtension(path).Length - 3) + Convert.ToString("(" + i + ")") + ".jpg";
                                }
                            }
                            catch
                            {

                            }


                        }
                        using (var fileStream = new FileStream(_app.WebRootPath + "/Images/" + path, FileMode.Create))
                        {
                            await file.CopyToAsync(fileStream);
                        }
                        newfile = new Image
                        {
                            Source = "/Images/" + path,
                        };
                        db.Image.Add(newfile);
                        await db.SaveChangesAsync();
                        newfile = new Image
                        {
                            Source = "/Images/" + path,
                        };
                        Image imageId = await db.Image.FirstOrDefaultAsync(p => p.Source == "/Images/" + path);
                        PostImage postImage = new PostImage
                        {
                            Post_Id = postId,
                            Image_Id = imageId,
                        };
                        db.PostImage.Add(postImage);
                        await db.SaveChangesAsync();
                    }
                }
            }
            return RedirectToAction("MainView");
        }
        public async Task<IActionResult> PostAddPage()
        {
            return View();
        }
        [HttpPost]
        public async Task<IActionResult> RegistrationOver(IFormFile file, User user, DateTime datecringe)
        {
            if (user != null)
            {
                CookieOptions cookieLogin = new CookieOptions();
                cookieLogin.Expires = DateTime.Now.AddYears(2);
                Response.Cookies.Append("Login", user.Login, cookieLogin);
                CookieOptions cookiePass = new CookieOptions();
                cookiePass.Expires = DateTime.Now.AddDays(7);
                Response.Cookies.Append("Pass", user.Login, cookiePass);
            }
            user.birth_date = datecringe;
            string path;
            Image newfile;
            if (file != null)
            {
                path = file.FileName;
                int i = 0;
                while (System.IO.File.Exists(_app.WebRootPath + "/Images/" + path))
                {
                    i++;
                    try
                    {
                        if (i == 1)
                            path = Path.GetFileNameWithoutExtension(path) + Convert.ToString("(" + i + ")") + ".jpg";
                        else
                        {
                            path = Path.GetFileNameWithoutExtension(path).Substring(0, Path.GetFileNameWithoutExtension(path).Length - 3) + Convert.ToString("(" + i + ")") + ".jpg";
                        }
                    }
                    catch
                    {

                    }


                }
                using (var fileStream = new FileStream(_app.WebRootPath + "/Images/" + path, FileMode.Create))
                {
                    await file.CopyToAsync(fileStream);
                }
                newfile = new Image
                {
                    Source ="/Images/" + path,
                };
                db.Image.Add(newfile);
                await db.SaveChangesAsync();
                Image image = await db.Image.FirstOrDefaultAsync(p => p.Source == newfile.Source);
                user.id_Image = image;
            }

            db.Users.Add(user);
            await db.SaveChangesAsync();

            return RedirectToAction("Autorization");
        }
        public async Task<IActionResult> ListOfUsers(int? id, string login, string email, int page = 1, SortState sortOrder = SortState.IdAsc)
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
            var item = await users.Skip((page - 1) * pagesize).Take(pagesize).Include (b => b.id_Image).ToListAsync();
            IndexViewModel viewModel = new IndexViewModel
            {
                PageViewModel = new PageViewModel(count, page, pagesize),
                SortViewModel = new SortViewModel(sortOrder),
                FilterViewModel = new FilterViewModel(id, email, login),
                Users = item,
            };
            return View(viewModel);
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
                    return RedirectToAction("MainView");
                }
            }
            return NotFound();
        }
    }

}
