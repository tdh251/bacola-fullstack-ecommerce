import PageBreadcrumb from "@/components/common/PageBreadCrumb";
import PageMeta from "@/components/common/PageMeta";
import ProductTable from "@/components/tables/ProductTable";

export default function ProductManagement() {
  return (
    <div>
      <PageMeta
        title="React.js Blank Page | TailAdmin - React.js Admin Dashboard Template"
        description="This is React.js Blank Page for TailAdmin - React.js Tailwind CSS Admin Dashboard Template"
      />
      <PageBreadcrumb pageTitle="Quản lý sản phẩm" />
         <div className="space-y-6">
         <ProductTable />
         </div>
    </div>
  );
}
