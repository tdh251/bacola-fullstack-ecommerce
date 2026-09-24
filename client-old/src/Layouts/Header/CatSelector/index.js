import { MdDehaze, MdKeyboardArrowDown } from "react-icons/md";

function CatSelector() {
   return (
      <div className="catSelector d-flex align-items-center">
         <MdDehaze className="catIconBefore" />
         <span className="currentCat">All CATEGORIES</span>
         <MdKeyboardArrowDown className="catIconAfter" />
         <span className="catNote">Tổng 26 Danh Mục</span>
      </div>
   );
}

export default CatSelector;
