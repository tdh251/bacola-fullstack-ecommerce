import BannerImg from "../../../assets/images/banner.jpg";

function SafetyBanner() {
   return (
      <section className="mt-[30px] rounded-lg bg-[#F8EFEA]">
         <div className="px-10 max-w-[1200px] flex items-center justify-between mx-auto gap-[30px]">
            <div className="max-w-[550px] py-[60px]">
               <span className="text-[#9b9bb4] mb-3">Sức khỏe là vàng</span>
               <h4 className="font-semibold">
                  Chúng tôi luôn quan tâm, đặt sức khỏe của khách hàng lên hàng
                  đầu.
               </h4>
            </div>
            <img
               src={BannerImg}
               alt="Safety Banner"
               className="w-auto h-[230px] object-top"
            />
         </div>
      </section>
   );
}
export default SafetyBanner;
