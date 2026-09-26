import PageBreadcrumb from "@/components/common/PageBreadCrumb";
import PageMeta from "@/components/common/PageMeta";
import DangerZone from "@/components/UserProfile/DangerZone";
import Security from "@/components/UserProfile/Security";
import UserAddressCard from "@/components/UserProfile/UserAddressCard";
import UserMetaCard from "@/components/UserProfile/UserMetaCard";

export default function UserProfiles() {
  return (
    <>
      <PageMeta
        title="React.js Profile Dashboard | TailAdmin - React.js Admin Dashboard Template"
        description="This is React.js Profile Dashboard page for TailAdmin - React.js Tailwind CSS Admin Dashboard Template"
      />
      <PageBreadcrumb pageTitle="Profile" />
        <div className="space-y-6">
          <UserMetaCard />
          <UserAddressCard />
          <Security />
          <DangerZone />
      </div>
    </>
  );
}
