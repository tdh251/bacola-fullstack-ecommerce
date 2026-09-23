import { IoIosSearch } from "react-icons/io";

function SearchBar() {
   return (
      <form className="searchForm">
         <div className="formGroup">
            <input
               type="text"
               className="searchField"
               placeholder="Tìm kiếm sản phẩm..."
               name="q"
               // aria-label="Tìm kiếm sản phẩm"
            />
            <button className="searchSubmit d-flex-center" type="button">
               <IoIosSearch />
            </button>
         </div>
      </form>
   );
}

export default SearchBar;
