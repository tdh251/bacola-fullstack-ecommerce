import {
   Table,
   TableBody,
   TableCell,
   TableHeader,
   TableRow,
} from "@/components/ui/table";
import Badge from "@/components/ui/badge/Badge";
import {
   PencilSquareIcon,
   TrashIcon,
} from "@heroicons/react/24/outline";

interface Product {
   id: number;
   title: string;
   image: string;
   sku: string;
   category: string;
   price: number;
   oldPrice?: number | null;
   inStock: boolean;
   rating: number;
}

// Mock data sản phẩm mô phỏng dữ liệu Bacola
const productList: Product[] = [
   {
      id: 1,
      title: "All Natural Italian-Style Chicken Meatballs",
      image: "https://images.unsplash.com/photo-1529692236671-f1f6cf9683ba?w=100&auto=format&fit=crop&q=80",
      sku: "ZU49VOR",
      category: "Meats & Seafood",
      price: 180000,
      oldPrice: 230000,
      inStock: true,
      rating: 4.5,
   },
];

export default function ProductTable() {
   const handleEdit = (id: number) => {
      console.log("Edit product ID:", id);
   };

   const handleDelete = (id: number) => {
      if (window.confirm("Bạn có chắc chắn muốn xóa sản phẩm này?")) {
         console.log("Delete product ID:", id);
      }
   };

   return (
      <div className="overflow-hidden rounded-xl border border-gray-200 bg-white dark:border-white/5 dark:bg-white/3">
         <div className="max-w-full overflow-x-auto">
            <Table>
               {/* Table Header */}
               <TableHeader className="border-b border-gray-100 dark:border-white/5">
                  <TableRow>
                     <TableCell
                        isHeader
                        className="px-5 py-3 text-start text-theme-xs font-medium whitespace-nowrap text-gray-500 dark:text-gray-400"
                     >
                        Sản phẩm
                     </TableCell>
                     <TableCell
                        isHeader
                        className="px-5 py-3 text-start text-theme-xs font-medium whitespace-nowrap text-gray-500 dark:text-gray-400"
                     >
                        Mã SKU
                     </TableCell>
                     <TableCell
                        isHeader
                        className="px-5 py-3 text-start text-theme-xs font-medium whitespace-nowrap text-gray-500 dark:text-gray-400"
                     >
                        Danh mục
                     </TableCell>
                     <TableCell
                        isHeader
                        className="px-5 py-3 text-start text-theme-xs font-medium whitespace-nowrap text-gray-500 dark:text-gray-400"
                     >
                        Giá bán
                     </TableCell>
                     <TableCell
                        isHeader
                        className="px-5 py-3 text-start text-theme-xs font-medium whitespace-nowrap text-gray-500 dark:text-gray-400"
                     >
                        Kho hàng
                     </TableCell>
                     <TableCell
                        isHeader
                        className="px-5 py-3 text-end text-theme-xs font-medium whitespace-nowrap text-gray-500 dark:text-gray-400 sm:px-6"
                     >
                        Thao tác
                     </TableCell>
                  </TableRow>
               </TableHeader>

               {/* Table Body */}
               <TableBody className="divide-y divide-gray-100 dark:divide-white/5">
                  {productList.map((product) => (
                     <TableRow key={product.id}>
                        {/* Cột Tên & Ảnh sản phẩm */}
                        <TableCell className="px-5 py-4 text-start whitespace-nowrap sm:px-6">
                           <div className="flex items-center gap-3">
                              <div className="h-12 w-12 shrink-0 overflow-hidden rounded-lg border border-gray-100 bg-gray-50 dark:border-white/10 dark:bg-gray-800">
                                 <img
                                    width={48}
                                    height={48}
                                    src={product.image}
                                    alt={product.title}
                                    className="h-full w-full object-cover"
                                 />
                              </div>
                              <div className="max-w-[280px]">
                                 <span className="block truncate text-theme-sm font-medium text-gray-800 dark:text-white/90">
                                    {product.title}
                                 </span>
                                 <span className="block text-theme-xs text-gray-400">
                                    Đánh giá: ★ {product.rating}
                                 </span>
                              </div>
                           </div>
                        </TableCell>

                        {/* Cột SKU */}
                        <TableCell className="px-5 py-3 text-start text-theme-sm font-mono text-gray-500 dark:text-gray-400">
                           {product.sku}
                        </TableCell>

                        {/* Cột Danh mục */}
                        <TableCell className="px-5 py-3 text-start text-theme-sm text-gray-600 dark:text-gray-300">
                           {product.category}
                        </TableCell>

                        {/* Cột Giá */}
                        <TableCell className="px-5 py-3 text-start text-theme-sm">
                           <span className="font-semibold text-gray-900 dark:text-white">
                              {product.price.toLocaleString("vi-VN")} đ
                           </span>
                           {product.oldPrice && (
                              <span className="block text-theme-xs text-gray-400 line-through">
                                 {product.oldPrice.toLocaleString("vi-VN")} đ
                              </span>
                           )}
                        </TableCell>

                        {/* Cột Trạng thái tồn kho */}
                        <TableCell className="px-5 py-3 text-start text-theme-sm">
                           <Badge
                              size="sm"
                              color={product.inStock ? "success" : "error"}
                           >
                              {product.inStock ? "Còn hàng" : "Hết hàng"}
                           </Badge>
                        </TableCell>

                        {/* Cột Thao tác */}
                        <TableCell className="px-5 py-3 text-end text-theme-sm sm:px-6">
                           <div className="flex items-center justify-end gap-2">
                              <button
                                 type="button"
                                 onClick={() => handleEdit(product.id)}
                                 className="rounded-lg p-1.5 text-gray-500 transition-colors hover:bg-gray-100 hover:text-blue-600 dark:text-gray-400 dark:hover:bg-white/5 dark:hover:text-blue-400"
                                 title="Chỉnh sửa"
                              >
                                 <PencilSquareIcon className="h-4 w-4" />
                              </button>
                              <button
                                 type="button"
                                 onClick={() => handleDelete(product.id)}
                                 className="rounded-lg p-1.5 text-gray-500 transition-colors hover:bg-gray-100 hover:text-red-600 dark:text-gray-400 dark:hover:bg-white/5 dark:hover:text-red-400"
                                 title="Xóa"
                              >
                                 <TrashIcon className="h-4 w-4" />
                              </button>
                           </div>
                        </TableCell>
                     </TableRow>
                  ))}
               </TableBody>
            </Table>
         </div>
      </div>
   );
}