import { Link } from "react-router-dom";
import Button from "../common/Button";
import {
   ArrowsPointingOutIcon,
   HeartIcon,
   StarIcon,
} from "@heroicons/react/24/outline";

function ProductItem({ product }) {
   const {
      id,
      title,
      image,
      discount,
      recommended,
      organic,
      inStock,
      rating = 5,
      ratingCount = 1,
      oldPrice,
      price,
      buttonType = "add", // "add" | "select" | "read"
   } = product;

   const renderButton = () => {
      const baseBtnClass =
         "w-full h-[38px] rounded-full border border-[#2bbef9] text-[#2bbef9] font-medium text-[13px] hover:bg-[#2bbef9] hover:text-white transition-colors duration-200 flex items-center justify-center";
      if (!inStock) {
      }
      return (
         <button type="button" className={baseBtnClass}>
            Thêm vào giỏ hàng
         </button>
      );
   };

   return (
      <div className="relative group p-4 border-r border-[#edeeef] last:border-r-0 flex flex-col justify-between h-full bg-white transition-shadow hover:shadow-sm">
         {/* Badges top-left */}
         <div className="absolute top-4 left-4 z-10 flex flex-col gap-1.5 items-start pointer-events-none">
            {discount && (
               <span className="bg-[#2bbef9] text-white text-[11px] font-medium px-2 py-0.5 rounded-[4px]">
                  {discount}%
               </span>
            )}
            {recommended && (
               <span className="bg-[#5c6874] text-white text-[9px] font-medium tracking-wider px-2 py-0.5 rounded-[4px] uppercase">
                  RECOMMENDED
               </span>
            )}
            {organic && (
               <span className="bg-[#dcfce7] text-[#16a34a] text-[10px] font-medium px-2 py-0.5 rounded-[4px] uppercase">
                  ORGANIC
               </span>
            )}
         </div>

         <div className="flex flex-col gap-[15px] absolute right-[15px] top-[15px] z-10">
            <Button
               className={
                  "flex-center text-[#3e445a] border border-[#edeef5] w-[38px] h-[38px] rounded-full opacity-0 translate-x-5 group-hover:translate-x-0 group-hover:opacity-100 transition-all"
               }
            >
               <ArrowsPointingOutIcon className="w-[20px]" />
            </Button>
            <Button
               className={
                  "flex-center text-[#3e445a] border border-[#edeef5] w-[38px] h-[38px] rounded-full opacity-0 translate-x-5 group-hover:translate-x-0 group-hover:opacity-100 transition-all"
               }
            >
               <HeartIcon className="w-[20px]" />
            </Button>
         </div>

         {/* Product Image */}
         <div className="w-full max-h-[230px] flex-center my-3 overflow-hidden">
            <img
               src={image}
               alt={title}
               className="max-h-full max-w-full object-contain group-hover:scale-105 transition-transform duration-300"
            />
         </div>

         {/* Product Info */}
         <div className="flex flex-col flex-grow">
            <h6 className="font-semibold text-[#202435] leading-snug line-clamp-2 min-h-[40px] hover:text-[#2bbef9] transition-colors mb-2">
               <Link to={`/product/${id}`}>{title}</Link>
            </h6>

            {/* Stock status */}
            <span
               className={`text-[11px] font-bold uppercase tracking-wider mb-2 font-dosis ${
                  inStock ? "text-[#00b853]" : "text-[#d51243]"
               }`}
            >
               {inStock ? "IN STOCK" : "OUT OF STOCK"}
            </span>

            {/* Rating stars */}
            <div className="flex items-center gap-1 mb-2">
               <div className="flex items-center text-[#ffb800]">
                  {[...Array(5)].map((_, i) => (
                     <StarIcon
                        key={i}
                        className={`w-3.5 h-3.5 ${
                           i < Math.floor(rating)
                              ? "text-[#ffb800]"
                              : "text-[#d5d7de]"
                        }`}
                     />
                  ))}
               </div>
               <span className="text-[12px] text-[#8e93a6] font-medium ml-1">
                  {ratingCount}
               </span>
            </div>

            {/* Prices */}
            <div className="flex items-baseline gap-2 mb-4 font-dosis">
               {oldPrice && (
                  <span className="text-[#a0a5b8] text-[14px] line-through font-medium">
                     ${oldPrice}
                  </span>
               )}
               <span className="text-[#d51243] text-[17px] font-bold">
                  ${price}
               </span>
            </div>
         </div>

         {/* Action Button */}
         <div className="w-full mt-auto">{renderButton()}</div>
      </div>
   );
}

export default ProductItem;
