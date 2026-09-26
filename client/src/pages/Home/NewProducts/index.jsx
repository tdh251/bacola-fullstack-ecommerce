import { ArrowLongRightIcon } from "@heroicons/react/24/outline";
import { Link } from "react-router-dom";
import ProductItem from "../../../components/ProductItem";
import ProductImgDemo from "../../../assets/images/product-image-demo.jpg";

const BEST_SELLER_PRODUCTS = [
   {
      id: 1,
      title: "All Natural Italian-Style Chicken Meatballs",
      image: ProductImgDemo,
      discount: 23,
      recommended: true,
      organic: false,
      inStock: true,
      rating: 4,
      ratingCount: 1,
      oldPrice: 230000,
      price: 180000,
      buttonType: "add",
   },
   {
      id: 2,
      title: "Angie's Boomchickapop Sweet & Salty Kettle Corn",
      image: ProductImgDemo,
      discount: null,
      recommended: false,
      organic: false,
      inStock: true,
      rating: 4,
      ratingCount: 1,
      oldPrice: null,
      price: 195000,
      buttonType: "select",
   },
   {
      id: 3,
      title: "Field Roast Chao Cheese Creamy Original",
      image: ProductImgDemo,
      discount: 19,
      recommended: false,
      organic: true,
      inStock: false,
      rating: 5,
      ratingCount: 1,
      oldPrice: 590000,
      price: 480000,
      buttonType: "read",
   },
   {
      id: 4,
      title: "Blue Diamond Almonds Lightly Salted",
      image: ProductImgDemo,
      discount: 10,
      recommended: false,
      organic: true,
      inStock: true,
      rating: 5,
      ratingCount: 1,
      oldPrice: 285000,
      price: 260000,
      buttonType: "add",
   },
   {
      id: 5,
      title: "All Natural Italian-Style Chicken Meatballs",
      image: ProductImgDemo,
      discount: 23,
      recommended: true,
      organic: false,
      inStock: true,
      rating: 4,
      ratingCount: 1,
      oldPrice: 230000,
      price: 180000,
      buttonType: "add",
   },
   {
      id: 6,
      title: "Angie's Boomchickapop Sweet & Salty Kettle Corn",
      image: ProductImgDemo,
      discount: null,
      recommended: false,
      organic: false,
      inStock: true,
      rating: 4,
      ratingCount: 1,
      oldPrice: null,
      price: 195000,
      buttonType: "select",
   },
   {
      id: 7,
      title: "Field Roast Chao Cheese Creamy Original",
      image: ProductImgDemo,
      discount: 19,
      recommended: false,
      organic: true,
      inStock: false,
      rating: 5,
      ratingCount: 1,
      oldPrice: 590000,
      price: 480000,
      buttonType: "read",
   },
   {
      id: 8,
      title: "Blue Diamond Almonds Lightly Salted",
      image: ProductImgDemo,
      discount: 10,
      recommended: false,
      organic: true,
      inStock: true,
      rating: 5,
      ratingCount: 1,
      oldPrice: 285000,
      price: 260000,
      buttonType: "add",
   },
   {
      id: 9,
      title: "All Natural Italian-Style Chicken Meatballs",
      image: ProductImgDemo,
      discount: 23,
      recommended: true,
      organic: false,
      inStock: true,
      rating: 4,
      ratingCount: 1,
      oldPrice: 230000,
      price: 180000,
      buttonType: "add",
   },
   {
      id: 10,
      title: "Angie's Boomchickapop Sweet & Salty Kettle Corn",
      image: ProductImgDemo,
      discount: null,
      recommended: false,
      organic: false,
      inStock: true,
      rating: 4,
      ratingCount: 1,
      oldPrice: null,
      price: 195000,
      buttonType: "select",
   },
];

function NewProducts() {
   return (
      <section className="w-full mt-[120px]">
         {/* Header section */}
         <div className="flex items-center justify-between mb-4">
            <div>
               <h3 className="text-[#202435] font-dosis uppercase font-semibold">
                  Sản phẩm mới
               </h3>
               <p className="text-[12px] text-[#9b9bb4]">
                  Cập nhật những sản phẩm mới trong tháng 10 này.
               </p>
            </div>

            <Link
               to="/products"
               className="flex items-center gap-2 border border-[#e2e4ec] rounded-full px-4 py-1.5 text-[12px] text-[#71778e] font-medium hover:border-[#2bbef9] hover:text-[#2bbef9] transition-colors"
            >
               Xem tất cả
               <ArrowLongRightIcon className="w-4 h-4" />
            </Link>
         </div>

         {/* Grid Container 2 hàng x 5 cột */}
         <div className="border border-[#edeeef] rounded-[10px] overflow-hidden bg-[#edeeef]">
            <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 xl:grid-cols-5 gap-[1px]">
               {BEST_SELLER_PRODUCTS.slice(0, 10).map((product) => (
                  <div key={product.id} className="bg-white h-full">
                     <ProductItem product={product} />
                  </div>
               ))}
            </div>
         </div>
      </section>
   );
}

export default NewProducts;
