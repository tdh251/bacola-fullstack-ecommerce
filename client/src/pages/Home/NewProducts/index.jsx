import { ArrowLongRightIcon } from "@heroicons/react/24/outline";
import { Link } from "react-router-dom";
import Product from "../../../components/Product";
import { PRODUCTS_DEMO } from "../../../data";

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
               {PRODUCTS_DEMO.slice(0, 10).map((product) => (
                  <div key={product.id} className="bg-white h-full">
                     <Product product={product} />
                  </div>
               ))}
            </div>
         </div>
      </section>
   );
}

export default NewProducts;
