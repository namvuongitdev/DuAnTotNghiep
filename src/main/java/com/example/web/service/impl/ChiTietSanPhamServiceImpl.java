package com.example.web.service.impl;

import com.example.web.model.ChiTietSanPham;
import com.example.web.model.MauSac;
import com.example.web.model.Size;
import com.example.web.repository.IChiTietSanPhamRepository;
import com.example.web.request.UpdateChiTietSanPham;
import com.example.web.response.ChiTietOnllineResponse;
import com.example.web.response.ChiTietResponse;
import com.example.web.response.ChiTietSanPhamResponse;
import com.example.web.service.IChiTietSanPhamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ChiTietSanPhamServiceImpl implements IChiTietSanPhamService {

    @Autowired
    private IChiTietSanPhamRepository iChiTietSanPhamRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<ChiTietSanPham> findAll(Pageable pageable) {
        return iChiTietSanPhamRepository.findAll(pageable);
    }

    @Override
    public ChiTietSanPham save(ChiTietSanPham chiTietSanPham) {
        ChiTietSanPham ctsp = iChiTietSanPhamRepository.save(chiTietSanPham);
        return ctsp;
    }

    @Override
    public ChiTietSanPham getOne(UUID id) {
        return iChiTietSanPhamRepository.getOne(id);
    }

    @Override
    public List<ChiTietSanPham> getChiTietSanPham(String id) {
        List<ChiTietSanPham> chiTietSanPhams = iChiTietSanPhamRepository.findBySanPham_Id(UUID.fromString(id));
        return chiTietSanPhams;
    }

    @Override
    public List<ChiTietSanPhamResponse> getCTSP(String id) {
        List<ChiTietSanPhamResponse> ctsp = iChiTietSanPhamRepository.findBySanPham_Id(UUID.fromString(id))
                .stream().map(chiTietSanPham -> modelMapper.map(chiTietSanPham, ChiTietSanPhamResponse.class)).collect(Collectors.toList());

        return ctsp;
    }

    @Override
    public ChiTietSanPhamResponse getByMauSacAndKichCoAndSanPham(String idMS, String idKC, String idSP) {
        Optional<ChiTietSanPham> chiTietSanPham = iChiTietSanPhamRepository.getChiTietSanPhamByMauSac_IdAndSize_IdAndSanPham_Id(idMS, idKC, idSP);
        if (chiTietSanPham.isPresent()) {
            ChiTietSanPhamResponse ctsp = modelMapper.map(chiTietSanPham.get(), ChiTietSanPhamResponse.class);
            return ctsp;
        } else {
            return null;
        }
    }

    @Override
    public List<ChiTietSanPham> listCTSPTheoIdSP(UUID idSP) {
        return iChiTietSanPhamRepository.listCTSPTheoIdSP(idSP);
    }

    @Override
    public ChiTietOnllineResponse getChiTietSanPhamByMauSac_IdAndSize_IdAndSanPham_Id1(UUID mauSac_Id, String size, UUID sanPham_Id) {
        return iChiTietSanPhamRepository.getChiTietSanPhamByMauSac_IdAndSize_IdAndSanPham_Id1(mauSac_Id, size, sanPham_Id);
    }

    @Override
    public void updateTT_0(UUID idSP) {
        iChiTietSanPhamRepository.updateTT_0(idSP);
    }

    @Override
    public void updateTT_1(UUID idSP) {
        iChiTietSanPhamRepository.updateTT_1(idSP);
    }

    @Override
    public String save2(String idCt, String idSp, Integer trangThai) {
        ChiTietSanPham ctsp = iChiTietSanPhamRepository.getOne(UUID.fromString(idCt));
        if (trangThai == 1) {
            ctsp.setTrangThai(0);
        } else {
            ctsp.setTrangThai(1);
        }
        iChiTietSanPhamRepository.save(ctsp);
        return "redirect:/admin/san-pham/hien-thi/" + ctsp.getSanPham().getId();
    }

    @Override
    public ChiTietResponse getChiTietSanPhamByMauSac_IdAndSize_IdAndIdSP(UUID mauSac_Id, String size, UUID sanPham_Id) {
        return iChiTietSanPhamRepository.getChiTietSanPhamByMauSac_IdAndSize_IdAndIdSP(mauSac_Id, size, sanPham_Id);

    }

    @Override
    public List<Size> getSizeBySanPham_idAndMauSac_id(UUID idSP, UUID idMS) {
        return iChiTietSanPhamRepository.findSizeBySanPham_idAndMauSac_id(idSP, idMS);
    }

    @Override
    public List<Object[]> getTop5SanPhamBanChay() {
        return iChiTietSanPhamRepository.getTop5SanPhamBanChay();
    }

    @Override
    public UUID getByQrcode(String qrCode) {
        Optional<UUID> value = iChiTietSanPhamRepository.findByQrCode(qrCode);
        if (value.isPresent()) {
            return value.get();
        } else {
            return null;
        }
    }

    public ChiTietSanPham checkSizeMauSac(UUID mauSac, String kichCo, UUID idSanPham) {
        return iChiTietSanPhamRepository.checkSizeMauSac(mauSac, kichCo, idSanPham);
    }

    @Override
    public Object[] getQrCodeById(UUID uuid) {
        return iChiTietSanPhamRepository.findQrCodeById(uuid);
    }

    public ChiTietSanPham getSL(String idGioHangCT) {
        return iChiTietSanPhamRepository.getSL(idGioHangCT);
    }

    @Override
    public Integer updateChiTietSanPham(List<UUID> chiTietSanPham , List<Integer> soLuong ,UUID idSP) {
         if(chiTietSanPham.isEmpty() || chiTietSanPham == null){
              return 1;
         }else{
            for(int i = 0 ; i < chiTietSanPham.size() ; i++){
                Optional<ChiTietSanPham> ctsp = iChiTietSanPhamRepository.findById( chiTietSanPham.get(i));
                if(ctsp.isPresent()){
                    ChiTietSanPham update = ctsp.get();
                    update.setSoLuong(soLuong.get(i));
                    iChiTietSanPhamRepository.save(update);
                }
            }
         }
        return 2;
    }

    @Override
    public Map<String , Boolean> updateMauSacAndKichCo(UpdateChiTietSanPham request , MauSac mauSac , Size size) {
        Map <String ,Boolean> response = new HashMap<>();
        ChiTietSanPham checkSizeMauSac = iChiTietSanPhamRepository.checkSizeMauSac(mauSac.getId() , size.getId() , request.getIdSP());
        if(checkSizeMauSac != null){
            response.put("error" , false);
        }else{
            ChiTietSanPham ctsp = iChiTietSanPhamRepository.getOne(request.getIdCTSP());
            ctsp.setSize(size);
            ctsp.setMauSac(mauSac);
            ChiTietSanPham chiTietSanPham = iChiTietSanPhamRepository.save(ctsp);
            if(chiTietSanPham != null){
                response.put("success" , true);
            }else{
                 response.put("error" , false);
            }
        }
        return response;
    }

}
