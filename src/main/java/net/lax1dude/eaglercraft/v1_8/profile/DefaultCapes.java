package net.lax1dude.eaglercraft.v1_8.profile;

import net.minecraft.util.ResourceLocation;

/**
 * Copyright (c) 2024 lax1dude. All Rights Reserved.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 */
public enum DefaultCapes {

	NO_CAPE(0, "No Cape", null),
	MINECON_2011(1, "Fire1", new ResourceLocation("eagler:capes/01.minecon_2011.png")),
	MINECON_2012(2, "Fire2", new ResourceLocation("eagler:capes/02.minecon_2012.png")),
	MINECON_2013(3, "Soon", new ResourceLocation("eagler:capes/02.minecon_2012.png"));

	
	public static final DefaultCapes[] defaultCapesMap = new DefaultCapes[2];
	
	public final int id;
	public final String name;
	public final ResourceLocation location;
	
	private DefaultCapes(int id, String name, ResourceLocation location) {
		this.id = id;
		this.name = name;
		this.location = location;
	}
	
	public static DefaultCapes getCapeFromId(int id) {
		DefaultCapes e = null;
		if(id >= 0 && id < defaultCapesMap.length) {
			e = defaultCapesMap[id];
		}
		if(e != null) {
			return e;
		}else {
			return NO_CAPE;
		}
	}
	
	static {
		DefaultCapes[] capes = values();
		for(int i = 0; i < capes.length; ++i) {
			defaultCapesMap[capes[i].id] = capes[i];
		}
	}

}
