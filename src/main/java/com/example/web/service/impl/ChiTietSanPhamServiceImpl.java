package com.example.web.service.impl;
import com.example.web.model.ChiTietSanPham;
import com.example.web.repository.IChiTietSanPhamRepository;
import com.example.web.response.ChiTietOnllineResponse;
import com.example.web.response.ChiTietResponse;
import com.example.web.response.ChiTietSanPhamResponse;
import com.example.web.service.IChiTietSanPhamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
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
       List<ChiTietSanPham> chiTietSanPhams =  iChiTietSanPhamRepository.findBySanPham_Id(UUID.fromString(id));
        return chiTietSanPhams;
    }

    @Override
    public List<ChiTietSanPhamResponse> getCTSP(String id) {
            List<ChiTietSanPhamResponse> ctsp = iChiTietSanPhamRepository.findBySanPham_Id(UUID.fromString(id))
                    .stream().map(chiTietSanPham -> modelMapper.map(chiTietSanPham , ChiTietSanPhamResponse.class)).collect(Collectors.toList());

        return ctsp;
    }

    @Override
    public ChiTietSanPhamResponse getByMauSacAndKichCoAndSanPham(String idMS, String idKC , String idSP) {
       Optional<ChiTietSanPham> chiTietSanPham =  iChiTietSanPhamRepository.getChiTietSanPhamByMauSac_IdAndSize_IdAndSanPham_Id(idMS , idKC , idSP);
        if (chiTietSanPham.isPresent()){
            ChiTietSanPhamResponse ctsp = modelMapper.map(chiTietSanPham.get() , ChiTietSanPhamResponse.class);
            return ctsp;
        }else {
            return null;
        }
    }

    @Override
    public List<ChiTietSanPham> listCTSPTheoIdSP(UUID idSP) {
        return iChiTietSanPhamRepository.listCTSPTheoIdSP(idSP);
    }

    @Override
    public ChiTietOnllineResponse getChiTietSanPhamByMauSac_IdAndSize_IdAndSanPham_Id1(UUID mauSac_Id, String size, UUID sanPham_Id) {
        return iChiTietSanPhamRepository.getChiTietSanPhamByMauSac_IdAndSize_IdAndSanPham_Id1(mauSac_Id,size,sanPham_Id);
    }

    @Override

    public ChiTietResponse getChiTietSanPhamByMauSac_IdAndSize_IdAndIdSP(UUID mauSac_Id, String size, UUID sanPham_Id) {
        return iChiTietSanPhamRepository.getChiTietSanPhamByMauSac_IdAndSize_IdAndIdSP(mauSac_Id,size,sanPham_Id);
    }
}
