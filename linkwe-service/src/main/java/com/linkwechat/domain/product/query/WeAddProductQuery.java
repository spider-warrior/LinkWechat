package com.linkwechat.domain.product.query;

import com.linkwechat.domain.media.WeMessageTemplate;
import com.linkwechat.domain.wecom.query.product.QwAddProductQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author danmo
 * @date 2022年09月30日 11:51
 */
@ApiModel
@Data
public class WeAddProductQuery {

    @NotBlank(message = "商品封面不能为空")
    @ApiModelProperty("商品封面地址")
    private String picture;

    @NotBlank(message = "商品描述不能为空")
    @ApiModelProperty("商品描述")
    private String describe;

    @NotBlank(message = "商品价格不能为空")
    @ApiModelProperty("商品价格")
    private String price;

    @Size(min = 1, max = 8,message = "商品附件最少1个最多8个")
    @ApiModelProperty("商品附件")
    private List<WeMessageTemplate> attachments;

    @ApiModelProperty(value = "商品编码",hidden = true)
    private String productSn;

    public QwAddProductQuery convert2Qw(){
        QwAddProductQuery query = new QwAddProductQuery();
        query.setProduct_sn(this.productSn);
        query.setDescription(this.describe);
        query.setPrice(new BigDecimal(price).multiply(BigDecimal.valueOf(100)).longValue());
        query.setMessageTemplates(this.attachments);
        return query;
    }
}
