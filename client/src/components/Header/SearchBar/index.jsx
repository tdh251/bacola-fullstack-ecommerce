import { MagnifyingGlassIcon } from "@heroicons/react/24/outline";

function SearchBar() {
   return (
      <form className="flex-grow relative">
         <input
            type="text"
            className="ps-[30px] pe-[60px] h-[60px] text-[15px] bg-[#f3f4f7] text-[#202435] w-[100%] rounded-[7px]"
            placeholder="Tìm kiếm sản phẩm..."
            name="q"
         />
         <button
            type="button"
            className="flex-center absolute top-0 right-0 bg-transparent text-[26px] w-[60px] h-[60px]"
         >
            <MagnifyingGlassIcon className="w-[18px] h-auto" />
         </button>
      </form>
   );
}

export default SearchBar;
