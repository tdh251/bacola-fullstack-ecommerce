import { ArrowLongRightIcon } from "@heroicons/react/24/outline";
import { Link } from "react-router-dom";
import BlogImgDemo from "../../../assets/images/blog-demo.jpg";

export const BLOG_POSTS = [
   {
      id: 1,
      title: "Bí quyết chọn rau củ hữu cơ tươi ngon và an toàn cho cả gia đình",
      category: "MẸO HAY",
      date: "26-09-2026",
      commentsCount: 5,
      author: "Bacola",
      image: BlogImgDemo,
   },
   {
      id: 2,
      title: "Thực phẩm giàu vitamin tăng sức đề kháng mỗi ngày bạn nên biết",
      category: "SỨC KHỎE",
      date: "26-09-2026",
      commentsCount: 3,
      author: "Bacola",
      image: BlogImgDemo,
   },
   {
      id: 3,
      title: "Cách bảo quản trái cây trong tủ lạnh giữ trọn vị tươi nguyên bản",
      category: "NHÀ BẾP",
      date: "26-09-2026",
      commentsCount: 8,
      author: "Bacola",
      image: BlogImgDemo,
   },
   {
      id: 4,
      title: "Gợi ý thực đơn trà chiều nhẹ nhàng cùng bánh ngọt thanh mát",
      category: "ẨM THỰC",
      date: "26-09-2026",
      commentsCount: 2,
      author: "Bacola",
      image: BlogImgDemo,
   },
];

function BlogItem({ post }) {
   const { id, title, category, date, commentsCount, author, image } = post;

   return (
      <article className="flex flex-col group">
         {/* Ảnh bài viết */}
         <Link
            to={`/blog/${id}`}
            className="block w-full h-[220px] rounded-[10px] overflow-hidden bg-gray-100 mb-4"
         >
            <img
               src={image}
               alt={title}
               className="w-full h-full object-cover group-hover:scale-105 transition-transform duration-300 ease-in-out"
            />
         </Link>

         {/* Danh mục */}
         <span className="text-[11px] font-semibold uppercase text-[#9b9bb4] tracking-[0.5px] mb-1.5">
            {category}
         </span>

         {/* Tiêu đề bài viết */}
         <h5 className="font-semibold text-[#202435] leading-snug line-clamp-2 min-h-[44px] mb-2 group-hover:text-[#2bbef9] transition-colors">
            <Link to={`/blog/${id}`}>{title}</Link>
         </h5>

         {/* Metadata: Ngày đăng, bình luận, tác giả */}
         <div className="flex items-center text-[12px] text-[#8e93a6] gap-3">
            <span>{date}</span>
            <span>{commentsCount} bình luận</span>
            <span>
               từ <span className="text-[#202435] font-medium">{author}</span>
            </span>
         </div>
      </article>
   );
}

function Blog() {
   return (
      <section className="mt-[100px]">
         <div className="flex items-center justify-between mb-4">
            <div>
               <h3 className="text-[#202435] font-dosis uppercase font-semibold">
                  Tin tức
               </h3>
               <p className="text-[12px] text-[#9b9bb4]">
                  Cập nhật thông tin về sức khỏe.
               </p>
            </div>

            <Link
               to="/blogs"
               className="flex items-center gap-2 border border-[#e2e4ec] rounded-full px-4 py-1.5 text-[12px] text-[#71778e] font-medium hover:border-[#2bbef9] hover:text-[#2bbef9] transition-colors"
            >
               Xem tất cả
               <ArrowLongRightIcon className="w-4 h-4" />
            </Link>
         </div>

         <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
            {BLOG_POSTS.map((post) => (
               <BlogItem key={post.id} post={post} />
            ))}
         </div>
      </section>
   );
}

export default Blog;
