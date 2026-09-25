import { ShoppingBagIcon } from "@heroicons/react/24/outline";

function MiniCart() {
   return (
      <div className="flex items-center gap-[15px]">
         <span className="font-dosis font-semibold">$0.00</span>
         <button className="flex-center relative w-[42px] h-[42px] rounded-full text-[#ea2b0f] border border-[#fff1ee] bg-[#fff1ee]">
            <span className="flex-center absolute top-0 right-[-4px] text-[10px] bg-[#ea2b0f] text-white min-w-[18px] h-[18px] rounded-full">
               0
            </span>
            <ShoppingBagIcon className="w-[18px] h-auto" />
         </button>
      </div>
   );
}

export default MiniCart;
