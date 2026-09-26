import { Link } from "react-router-dom";
import BannerImg1 from "../../../assets/images/banner-1.jpg";
import BannerImg2 from "../../../assets/images/banner-2.jpg";
import BannerImg3 from "../../../assets/images/banner-3.jpg";

const PROMO_DATA = [
   {
      id: 1,
      badge: "Giảm giá 40%",
      title: "Hạt và ngũ cốc",
      desc: "Mang lại những dưỡng chất cần thiết cho gia đình bạn",
      image: BannerImg1,
      link: "/category/legumes-cereals",
      bgColor: "bg-[#E8EDF1]",
   },
   {
      id: 2,
      badge: "Giảm giá 40%",
      title: "Trứng và sữa",
      desc: "Thực phẩm giàu chất béo và protein lành mạnh",
      image: BannerImg2,
      link: "/category/dairy-eggs",
      bgColor: "bg-[#EEEEEE]",
   },
   {
      id: 3,
      badge: "Giảm giá 40%",
      title: "Trà và cà phê",
      desc: "Một cốc trà/cà phê để tinh thần thoải mái ngày dài",
      image: BannerImg3,
      link: "/category/dairy-eggs",
      bgColor: "bg-[#EDF1F3]",
   },
];

function PromoBanners() {
   return (
      <section className="w-full mt-[30px]">
         <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
            {PROMO_DATA.map((item) => (
               <div
                  key={item.id}
                  className={`relative rounded-[12px] overflow-hidden ${item.bgColor} min-h-[220px] flex flex-col justify-between p-6 sm:p-9 group bg-right-top bg-no-repeat`}
                  style={{ backgroundImage: `url(${item.image})` }}
               >
                  <div className="relative z-10 max-w-[55%] flex flex-col items-start">
                     <span className="text-[14px] font-semibold text-[#00b853] uppercase tracking-[0.5px] mb-2 font-dosis">
                        {item.badge}
                     </span>
                     <h4 className="font-bold text-[#202435] leading-tight mb-1 font-dosis">
                        {item.title}
                     </h4>
                     <p className="text-[12px] text-[#9b9bb4] mb-5">
                        {item.desc}
                     </p>
                  </div>
                  <Link
                     to={item.link}
                     className="flex-center bg-[#b8bac7] hover:bg-[#2bbef9] text-white text-[12px] font-semibold px-5 py-2 rounded-full transition-colors duration-200 w-fit"
                  >
                     Mua sắm ngay
                  </Link>
               </div>
            ))}
         </div>
      </section>
   );
}

export default PromoBanners;
